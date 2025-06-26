"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { fetchMarkets, addNewMarket } from "@/app/services/marketService";
import MarketCard from "@/app/components/MarketCard";
import { Market } from "@/app/types";

export default function MarketsPage() {
  const [markets, setMarkets] = useState<Market[]>([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false); // State to control modal visibility
  const [newMarket, setNewMarket] = useState({
    name: "",
    categories: [""], // Initialize with one empty category
  }); // State to store new market details
  const [appId, setAppId] = useState<string>("");
  const router = useRouter();

  useEffect(() => {
    if (typeof window !== "undefined") {
      const storedAppId = localStorage.getItem("appId");
      console.log("Trying to load appId from localStorage:", storedAppId);
      if (storedAppId) setAppId(storedAppId);
    }
  }, []);
  useEffect(() => {
    if (!appId) return;
    const loadMarkets = async () => {
      try {
        console.log("appId : ", appId);
        const data = (await fetchMarkets(appId)) as Market[];
        setMarkets(data);
        console.log(data);
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
      `/dashboard/markets/${marketId}?name=${encodeURIComponent(marketName)}`
    );
  };

  const handleAddMarket = () => {
    setShowModal(true); // Show the modal
  };

  const handleCloseModal = () => {
    setShowModal(false); // Hide the modal
    setNewMarket({ name: "", categories: [""] }); // Reset the form to only include the name field
  };

  const handleSubmitMarket = async () => {
    try {
      await addNewMarket(appId, {
        name: newMarket.name,
        categoriesIds: newMarket.categories,
      });
      setShowModal(false); // Close the modal
      setNewMarket({ name: "", categories: [""] }); // Reset the form to only include the name field
      const updatedMarkets = (await fetchMarkets(appId)) as Market[]; // Refresh the markets list
      setMarkets(updatedMarkets);
    } catch (error: any) {
      console.error("Failed to add new market:", error.message);
    }
  };

  return (
    <main className="p-6 space-y-8">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold">Your Markets</h1>
        <button
          onClick={handleAddMarket}
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
        >
          + Add Market
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

      {/* Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded shadow-lg w-96">
            <h2 className="text-xl font-bold mb-4 text-black">
              Add New Market
            </h2>
            <div className="space-y-4">
              {/* Market Name Field */}
              <input
                type="text"
                placeholder="Market Name"
                className="w-full p-2 border rounded text-black placeholder-black"
                value={newMarket.name}
                onChange={(e) =>
                  setNewMarket((prev) => ({ ...prev, name: e.target.value }))
                }
              />

              {/* Categories Section */}
              <div>
                <h3 className="text-lg font-semibold text-black mb-2">
                  Categories
                </h3>
                {newMarket.categories.map((category, index) => (
                  <div key={index} className="flex items-center space-x-2 mb-2">
                    <input
                      type="text"
                      placeholder={`Category ${index + 1}`}
                      className="w-full p-2 border rounded text-black placeholder-black"
                      value={category}
                      onChange={(e) =>
                        setNewMarket((prev) => {
                          const updatedCategories = [...prev.categories];
                          updatedCategories[index] = e.target.value;
                          return { ...prev, categories: updatedCategories };
                        })
                      }
                    />
                    {/* Remove Category Button */}
                    {index > 0 && (
                      <button
                        onClick={() =>
                          setNewMarket((prev) => ({
                            ...prev,
                            categories: prev.categories.filter(
                              (_, i) => i !== index
                            ),
                          }))
                        }
                        className="px-2 py-1 bg-red-500 text-white rounded hover:bg-red-600"
                      >
                        -
                      </button>
                    )}
                  </div>
                ))}
                {/* Add Category Button */}
                <button
                  onClick={() =>
                    setNewMarket((prev) => ({
                      ...prev,
                      categories: [...prev.categories, ""],
                    }))
                  }
                  className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600"
                >
                  + Add Category
                </button>
              </div>
            </div>

            {/* Modal Buttons */}
            <div className="flex justify-end space-x-4 mt-4">
              <button
                onClick={handleCloseModal}
                className="px-4 py-2 bg-gray-300 text-black rounded hover:bg-gray-400"
              >
                Cancel
              </button>
              <button
                onClick={handleSubmitMarket}
                className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
              >
                Add
              </button>
            </div>
          </div>
        </div>
      )}
    </main>
  );
}
