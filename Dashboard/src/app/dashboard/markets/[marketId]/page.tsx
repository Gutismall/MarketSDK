// app/markets/[marketId]/page.tsx
"use client";

import { useEffect, useState } from "react";
import { useParams, useRouter } from "next/navigation";
import { getCategoriesForMarket } from "@/app/services/categoryService";
import { getPostsForCategory } from "@/app/services/postService";
import { useSearchParams } from "next/navigation";
import { Category, Post } from "@/app/types";

export default function MarketDetailsPage() {
  const { marketId } = useParams<{ marketId: string }>();
  const router = useRouter();
  const [categories, setCategories] = useState<Category[]>([]);
  const [postsByCategory, setPostsByCategory] = useState<{
    [key: string]: Post[];
  }>({});
  const [expandedCategories, setExpandedCategories] = useState<{
    [key: string]: boolean;
  }>({});
  const [expandedPosts, setExpandedPosts] = useState<{
    [key: string]: boolean;
  }>({});
  const [appId, setAppId] = useState<string | null>(null); // State for appId
  const marketName = useSearchParams().get("name");

  useEffect(() => {
    // Retrieve appId from localStorage on the client side
    const storedAppId = localStorage.getItem("appId");
    setAppId(storedAppId);
  }, []);

  useEffect(() => {
    async function fetchData() {
      if (!appId) return; // Ensure appId is available before making API calls

      try {
        // Fetch categories for the market
        const cats = (await getCategoriesForMarket(
          appId,
          marketId
        )) as Category[];
        console.log(cats);
        setCategories(cats);

        // Initialize an object to store posts by category
        const postsMap: { [key: string]: Post[] } = {};

        // Iterate over each category
        for (const cat of cats) {
          const posts = (await getPostsForCategory(
            appId,
            marketId,
            cat.categoryId
          )) as Post[];
          console.log("category id:", cat.categoryId);
          console.log("posts :", posts);
          postsMap[cat.categoryId] = posts;
        }

        // Update state with posts mapped by category
        setPostsByCategory(postsMap);
      } catch (error) {
        console.error("Failed to fetch data:", error);
      }
    }

    fetchData();
  }, [appId, marketId]); // Add appId as a dependency

  // Toggle category expansion
  const toggleCategory = (categoryId: string) => {
    setExpandedCategories((prev) => ({
      ...prev,
      [categoryId]: !prev[categoryId],
    }));
  };

  // Toggle post expansion
  const togglePost = (postId: string) => {
    setExpandedPosts((prev) => ({
      ...prev,
      [postId]: !prev[postId],
    }));
  };

  return (
    <div className="p-6 relative">
      {/* Go Back Button */}
      <button
        onClick={() => router.push("/dashboard")}
        className="absolute top-4 right-4 bg-gray-300 text-black px-3 py-1 rounded hover:bg-gray-400"
      >
        Go Back
      </button>

      <h1 className="text-2xl font-bold mb-4">Market: {marketName}</h1>
      {categories.map((cat) => (
        <div key={cat.categoryId} className="mb-6">
          {/* Category Header */}
          <h2
            className="text-xl font-semibold mb-2 cursor-pointer"
            onClick={() => toggleCategory(cat.categoryId)}
          >
            {cat.name} {expandedCategories[cat.categoryId] ? "▼" : "▶"}
          </h2>

          {/* Posts List */}
          {expandedCategories[cat.categoryId] && (
            <ul className="ml-4 list-disc">
              {(postsByCategory[cat.categoryId] || []).map((post) => (
                <li key={post.postId} className="mb-2">
                  {/* Post Header */}
                  <div
                    className="cursor-pointer"
                    onClick={() => togglePost(post.postId)}
                  >
                    {post.title} {expandedPosts[post.postId] ? "▼" : "▶"}
                  </div>

                  {/* Post Details */}
                  {expandedPosts[post.postId] && (
                    <div className="ml-4 mt-2 border-l pl-4">
                      <p>
                        <strong>Description:</strong> {post.description}
                      </p>
                      <p>
                        <strong>Price:</strong> ${post.price}
                      </p>
                      <p>
                        <strong>Country:</strong> {post.country || "N/A"}
                      </p>
                      <p>
                        <strong>City:</strong> {post.city || "N/A"}
                      </p>
                      <p>
                        <strong>Created At:</strong> {post.createdAt}
                      </p>
                      {post.images && post.images.length > 0 && (
                        <div>
                          <strong>Images:</strong>
                          <ul>
                            {post.images.map((image, index) => (
                              <li key={index}>
                                <img
                                  src={image}
                                  alt={`Post Image ${index + 1}`}
                                  className="w-32 h-32 object-cover mt-2"
                                />
                              </li>
                            ))}
                          </ul>
                        </div>
                      )}
                    </div>
                  )}
                </li>
              ))}
            </ul>
          )}
        </div>
      ))}
    </div>
  );
}
