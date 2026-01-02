import { Outlet } from "react-router";
import Navbar from "../navbar/Navbar";

function MainLayout() {
  return (
    <div className="h-screen bg-zinc-800 text-zinc-50 flex flex-col">
      <Navbar />
      <main className="flex-1 overflow-hidden">
        <Outlet />
      </main>
    </div>
  );
}

export default MainLayout;
