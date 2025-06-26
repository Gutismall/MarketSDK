import React from "react";

interface MarketCardProps {
  name: string;
  numOfPosts: number;
  onClick: () => void;
}

export default function MarketCard({
  name,
  numOfPosts,
  onClick,
}: MarketCardProps) {
  return (
    <div
      onClick={onClick}
      className="p-4 border rounded-lg shadow hover:shadow-lg cursor-pointer transition"
    >
      <h3 className="text-lg font-semibold">{name}</h3>
      <p className="text-sm text-gray-500">Number Of Posts : {numOfPosts}</p>
    </div>
  );
}
