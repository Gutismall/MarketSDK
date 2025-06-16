"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { fetchMarkets, addNewMarket } from "@/app/services/marketService";
import MarketCard from "@/app/components/MarketCard";
import { Market } from "@/app/types";
import { createCategories } from "@/app/services/categoryService";

export default function MarketsPage() {
  const [markets, setMarkets] = useState<Market[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false); // State to control modal visibility
  const [newMarket, setNewMarket] = useState({
    name: "",
    categories: [""], // Initialize with one empty category
  }); // State to store new market details
  const [appId, setAppId] = useState<string | null>(null); // State for appId
  const router = useRouter();

  useEffect(() => {
    // Retrieve appId from localStorage on the client side
    const storedAppId = localStorage.getItem("appId");
    setAppId(storedAppId);
  }, []);

  useEffect(() => {
    const loadMarkets = async () => {
      if (!appId) return; // Ensure appId is available before making API calls

      try {
        const data = (await fetchMarkets(appId)) as Market[];
        setMarkets(data);
      } catch (error) {
        console.error("Failed to load markets", error);
      } finally {
        setLoading(false);
      }
    };
    loadMarkets();
  }, [appId]);

  const handleMarketClick = (marketId: string, marketName: string) => {
    router.push(
      `/dashboard/statistics/${marketId}?name=${encodeURIComponent(marketName)}`
    );
  };

  return (
    <main className="p-6 space-y-8">
      {/* Go Back Button */}
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          width: "100%",
          marginBottom: "20px",
        }}
      >
        <h1 className="text-3xl font-bold">Markets</h1>
        <button
          style={{
            backgroundColor: "#007BFF", // Button color
            color: "white", // Text color
            border: "none", // Remove border
            borderRadius: "5px", // Rounded corners
            padding: "10px 20px", // Padding for the button
            cursor: "pointer", // Pointer cursor on hover
          }}
          onClick={() => router.back()} // Navigate back to the previous page
        >
          Go Back
        </button>
      </div>

      {loading ? (
        <p>Loading markets...</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {markets.map((market: Market) => (
            <MarketCard
              key={market.marketId}
              name={market.name}
              numOfPosts={market.postIds?.length || 0} // Use optional chaining and fallback
              onClick={() => handleMarketClick(market.marketId, market.name)}
            />
          ))}
        </div>
      )}
    </main>
  );
}
