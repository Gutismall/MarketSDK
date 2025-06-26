// src/app/login/actions.ts
"use server";

import { cookies } from "next/headers";

export async function setAppIdCookie(appId: string) {
  const cookieStore = await cookies();
  cookieStore.set("appId", appId, {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production",
    sameSite: "strict",
    path: "/",
  });
}
