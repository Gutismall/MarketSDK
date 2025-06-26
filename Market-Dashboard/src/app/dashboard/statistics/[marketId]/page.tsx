// app/statistics/page.tsx
"use client";

import { useEffect, useState } from "react";
import { useParams, useSearchParams } from "next/navigation";
import { PostsPerCategory } from "@/app/components/charts/postsPerCategory";
import { getCategoriesForMarket } from "@/app/services/categoryService";
import { Category } from "@/app/types";
import { PostsPerDay } from "@/app/components/charts/PostsPerDay";
import { Label } from "@/components/ui/label";
import { DashboardCard } from "@/app/components/DashboardCard";
import {
  getNumberOfPostsPerMarket,
  fetchPriceOfPosts,
} from "@/app/services/postService";

export default function StatisticsPage() {
  const { marketId } = useParams(); // Get marketId from the URL
  const searchParams = useSearchParams();
  const marketName = searchParams.get("name"); // Get marketName from query parameters

  const [categoriesData, setCategoriesData] = useState<
    { name: string; postsCount: number }[]
  >([]);
  const [numOfPosts, setNumOfPosts] = useState(0);
  const [appId, setAppId] = useState<string>("");

  useEffect(() => {
    if (typeof window !== "undefined") {
      const storedAppId = localStorage.getItem("appId");
      if (storedAppId) setAppId(storedAppId);
    }
  }, []);
  const [numOfCategories, setNumOfCategories] = useState(0);
  const [priceOfPosts, setPriceOfPosts] = useState(0);

  useEffect(() => {
    if (!appId) return;
    async function fetchCategoriesData() {
      try {
        const categories = (await getCategoriesForMarket(
          appId,
          marketId as string
        )) as Category[];
        console.log(categories);
        const processedData = categories.map((category) => ({
          name: category.name,
          postsCount: category.postsIds.length,
        }));
        setCategoriesData(processedData);
        setNumOfCategories(categories.length);
      } catch (error) {
        console.error("Failed to fetch categories data:", error);
      }
    }
    fetchCategoriesData();
  }, [appId, marketId]);

  useEffect(() => {
    if (!appId) return;
    async function getNumOfPosts() {
      try {
        const numberOfPosts = (await getNumberOfPostsPerMarket(
          appId,
          marketId as String
        )) as number;
        console.log("count:", numberOfPosts);
        // Process and store the data as needed
        setNumOfPosts(numberOfPosts);
      } catch (error) {
        console.error("Failed to fetch additional data:", error);
      }
    }
    getNumOfPosts();
  }, [appId]); // This useEffect depends only on appId

  useEffect(() => {
    if (!appId) return;
    async function getPriceOfPosts() {
      try {
        const priceOfPoses = (await fetchPriceOfPosts(
          appId,
          marketId as string
        )) as number;
        console.log("count:", priceOfPoses);
        // Process and store the data as needed
        setPriceOfPosts(priceOfPoses);
      } catch (error) {
        console.error("Failed to fetch additional data:", error);
      }
    }
    getPriceOfPosts();
  }, [appId]); // This useEffect depends only on appId

  const postPerDayData = [
    { date: "2025-06-07", value: 20 },
    { date: "2025-06-08", value: 24 },
    { date: "2025-06-09", value: 15 },
    { date: "2025-06-10", value: 40 },
    { date: "2025-06-11", value: 1 },
    { date: "2025-06-12", value: 9 },
    { date: "2025-06-13", value: 13 },
  ];

  return (
    <div>
      {/* Tabs at the top */}
      <div
        style={{
          boxShadow: "0px 2px 5px rgba(0, 0, 0, 0.1)", // Optional: Add a shadow for better visibility
          flexDirection: "row", // Arrange items horizontally
          alignItems: "center", // Center items vertically
          position: "sticky", // Keeps the tabs at the top while scrolling
          top: 0, // Position at the top of the viewport
          backgroundColor: "white", // Optional: Add a background color
          zIndex: 10, // Ensure it stays above other content
          padding: "10px", // Add padding for spacing
          display: "flex", // Use flexbox for alignment
          justifyContent: "space-between", // Space items apart horizontally
          width: "100%",
        }}
      >
        <Label style={{ fontSize: 25 }}>
          Statistics for {marketName || "Market"}
        </Label>
        <button
          style={{
            backgroundColor: "#007BFF", // Button color
            color: "white", // Text color
            border: "none", // Remove border
            borderRadius: "5px", // Rounded corners
            padding: "10px 20px", // Padding for the button
            cursor: "pointer", // Pointer cursor on hover
          }}
          onClick={() => window.history.back()} // Go back to the previous page
        >
          Go Back
        </button>
      </div>
      <div className="grid grid-cols-1 md:gridcols-2 lg:grid-cols-4 gap-4 p-5">
        <DashboardCard
          title="Total Posts"
          subtitle="total posts in current market"
          body={numOfPosts.toString()}
        />
        <DashboardCard
          title="Total categories"
          subtitle="total categories in current market"
          body={numOfCategories.toString()}
        />
        <DashboardCard
          title="Total Price"
          subtitle="total Price in current market"
          body={priceOfPosts.toString() + " $ "}
        />
        <DashboardCard
          title="Number Of Post Today"
          subtitle="total number posts posted today on the market"
          body="2"
        />
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-4 p-5">
        <PostsPerCategory data={categoriesData} />
        <PostsPerDay data={postPerDayData} />
      </div>
    </div>
  );
}
