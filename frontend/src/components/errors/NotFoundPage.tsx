import { Link } from "react-router";

function NotFoundPage() {
  return (
    <div className="p-6">
      <div className="text-4xl font-bold mb-1">404</div>
      <h1 className="text-2xl mb-4">
        Page Not Found
      </h1>
      <p className="mb-4">
        The page you're looking for doesn't exist.
      </p>
      <Link className="text-lime-300 hover:text-lime-200 active:text-lime-300" to="/">Go Home</Link>
    </div>
  );
}

export default NotFoundPage;
