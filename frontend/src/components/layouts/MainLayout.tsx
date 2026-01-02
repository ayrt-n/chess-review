import { Outlet } from "react-router";
import Navbar from "../navbar/Navbar";

function MainLayout() {
  return (
    <div className="min-h-screen bg-zinc-800 text-zinc-50">
      <Navbar />
      <main>
        <Outlet />
      </main>
    </div>
  );
}

export default MainLayout;
