import SkeletonLoader from "../SkeletonLoader";

function GamesListSkeleton() {
  const gameListItem = () => {
    return (
      <SkeletonLoader rounded="none" className="h-20 w-full mb-1" />
    );
  };

  return (
    <div className="p-7 w-full max-w-[900px]">
      <div className="mb-4">
        <SkeletonLoader className="h-9 max-w-80" />
      </div>

      <SkeletonLoader rounded="none" className="rounded-t-lg h-12 w-full mb-1" />
      {gameListItem()}
      {gameListItem()}
      {gameListItem()}
      {gameListItem()}
      {gameListItem()}
      {gameListItem()}
    </div>
  );
}

export default GamesListSkeleton;
