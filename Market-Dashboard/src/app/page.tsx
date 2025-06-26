// app/page.tsx
"use client";
import { useEffect } from "react";
import { useRouter } from "next/navigation";

export default function Home() {
  const router = useRouter();

  useEffect(() => {
    router.push("/auth/login"); // ⬅️ redirect on load
  }, [router]);

  return null; // don't render anything
}
