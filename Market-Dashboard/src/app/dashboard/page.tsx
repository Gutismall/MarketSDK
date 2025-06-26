"use client";

import { useRouter } from "next/navigation";

export default function DashboardPage() {
  const router = useRouter();

  const handleNavigation = (path: string) => {
    router.push(path);
  };

  return (
    <main className="min-h-screen flex flex-col items-center justify-center p-8 space-y-8">
      <h1 className="text-3xl font-bold">
        Welcome to Your Marketplace Dashboard
      </h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 gap-6 w-full max-w-3xl">
        <div
          onClick={() => handleNavigation("/dashboard/markets")}
          className="p-6 border rounded-lg shadow hover:shadow-md cursor-pointer transition"
        >
          <h2 className="text-xl font-semibold">🛒 Manage Markets</h2>
          <p className="text-gray-600 mt-2">
            View your markets, categories, and posts. Create and manage your
            marketplace.
          </p>
        </div>

        <div
          onClick={() => handleNavigation("/dashboard/statistics")}
          className="p-6 border rounded-lg shadow hover:shadow-md cursor-pointer transition"
        >
          <h2 className="text-xl font-semibold">📊 View Statistics</h2>
          <p className="text-gray-600 mt-2">
            See real-time insights: number of posts, market activity, trends,
            and more.
          </p>
        </div>
      </div>
    </main>
  );
}
