// app/statistics/page.tsx
"use client";

import { useEffect, useState } from "react";
import { useParams, useSearchParams } from "next/navigation";
import { PostsPerCategory } from "@/app/components/charts/postsPerCategory";
import { getCategoriesForMarket } from "@/app/services/categoryService";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Category, Post } from "@/app/types";
import { PostsPerDay } from "@/app/components/charts/PostsPerDay";

export default function StatisticsPage() {
  const { marketId } = useParams(); // Get marketId from the URL
  const searchParams = useSearchParams();
  const marketName = searchParams.get("name"); // Get marketName from query parameters
  const [activeTab, setActiveTab] = useState<string>("User Engagement"); // Track the active tab
  const postsDataArray: { date: string; value: number }[] = []; // Array to store results

  const [categoriesData, setCategoriesData] = useState<
    { name: string; postsCount: number }[]
  >([]);
  const [appId, setAppId] = useState<string>("");

  useEffect(() => {
    if (typeof window !== "undefined") {
      const storedAppId = localStorage.getItem("appId");
      if (storedAppId) setAppId(storedAppId);
    }
  }, []);

  useEffect(() => {
    if (!appId) return;
    async function fetchCategoriesData() {
      try {
        const categories = (await getCategoriesForMarket(
          appId,
          marketId as string
        )) as Category[];
        const processedData = categories.map((category) => ({
          name: category.name,
          postsCount: category.postsIds.length,
        }));
        setCategoriesData(processedData);
      } catch (error) {
        console.error("Failed to fetch categories data:", error);
      }
    }
    fetchCategoriesData();
  }, [appId, marketId]);

  const postPerDayData = [
    { date: "2025-06-07", value: 20 },
    { date: "2025-06-07", value: 24 },
    { date: "2025-06-07", value: 15 },
    { date: "2025-06-07", value: 40 },
    { date: "2025-06-07", value: 1 },
    { date: "2025-06-07", value: 9 },
    { date: "2025-06-07", value: 13 },
  ];

  return (
    <div
      style={{
        display: "flex", // Enable Flexbox
        flexDirection: "column", // Arrange items vertically
        alignItems: "center", // Center items horizontally
        gap: "20px", // Add spacing between items
      }}
    >
      {/* Tabs at the top */}
      <div
        style={{
          boxShadow: "0px 2px 5px rgba(0, 0, 0, 0.1)", // Optional: Add a shadow for better visibility
          flexDirection: "column", // Arrange items vertically
          alignItems: "center", // Center items horizontally
          position: "sticky", // Keeps the tabs at the top while scrolling
          top: 0, // Position at the top of the viewport
          backgroundColor: "white", // Optional: Add a background color
          zIndex: 10, // Ensure it stays above other content
          padding: "10px", // Add padding for spacing
          display: "flex", // Use flexbox for alignment
          justifyContent: "center", // Center horizontally
          width: "100%",
        }}
      >
        <h1>Statistics for {marketName || "Market"}</h1>
        <Tabs
          defaultValue="User Engagement"
          className=""
          onValueChange={(value) => setActiveTab(value)} // Update activeTab state
        >
          <TabsList>
            <TabsTrigger value="User Engagement">User Engagement</TabsTrigger>
            <TabsTrigger value="Market Insights">Market Insights</TabsTrigger>
            <TabsTrigger value="Geographic Insights">
              Geographic Insights
            </TabsTrigger>
            <TabsTrigger value="Time-Based Trends">
              Time-Based Trends
            </TabsTrigger>
          </TabsList>
        </Tabs>
      </div>

      {/* Main content changes based on activeTab */}
      <div
        style={{
          width: "100%", // Make the div span the full width of its parent
          height: "auto", // Allow the height to adjust based on content
          padding: "20px", // Add padding for spacing
          boxSizing: "border-box", // Include padding in the width/height calculation
        }}
      >
        {activeTab === "User Engagement" && (
          <div
            style={{
              display: "flex",
              gap: "20px",
              width: "100%", // Ensure the container spans the full width
              alignItems: "stretch", // Stretch child elements to fill the container height
            }}
          >
            <div style={{ flex: 1 }}>
              {" "}
              {/* Allow the card to take equal space */}
              <PostsPerCategory data={categoriesData} />
            </div>
            <div style={{ flex: 1 }}>
              {" "}
              {/* Allow the card to take equal space */}
              <PostsPerDay data={postPerDayData} />
            </div>
          </div>
        )}
        {activeTab === "Market Insights" && <div></div>}
        {activeTab === "Geographic Insights" && (
          <div>
            <PostsPerDay data={postPerDayData} />
          </div>
        )}
        {activeTab === "Time-Based Trends" && (
          <div>
            <h2>Time-Based Trends</h2>
            <p>Details about time-based trends go here.</p>
          </div>
        )}
      </div>
    </div>
  );
}
