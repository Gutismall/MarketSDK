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
  data: { date: string; value: number }[]; // Array of category data
}

export function PostsPerDay({ data }: PostsPerCategoryProps) {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Posts Per Day</CardTitle>
        <CardDescription>
          Here you can see how many posts per day where added
        </CardDescription>
      </CardHeader>
      <CardContent>
        <ResponsiveContainer width="100%" height={400}>
          <BarChart
            data={data}
            margin={{ top: 20, right: 30, left: 0, bottom: 5 }}
          >
            <XAxis dataKey="date" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="value" fill="#8884d8" />
          </BarChart>
        </ResponsiveContainer>
      </CardContent>
    </Card>
  );
}
