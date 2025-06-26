"use client";

import {
  ResponsiveContainer,
  BarChart,
  Bar,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";

// Define props for the component
interface PostsPerCategoryProps {
  data: { name: string; postsCount: number }[]; // Array of category data
}

export function PostsPerCategory({ data }: PostsPerCategoryProps) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Posts Per Category</CardTitle>
        <CardDescription>
          Here you can see all the categories in your market and how many posts
          are in each one.
        </CardDescription>
      </CardHeader>
      <CardContent>
        <ResponsiveContainer width="100%" height={400}>
          <BarChart
            data={data}
            margin={{ top: 20, right: 30, left: 0, bottom: 5 }}
          >
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="postsCount" fill="#8884d8" />
          </BarChart>
        </ResponsiveContainer>
      </CardContent>
    </Card>
  );
}
