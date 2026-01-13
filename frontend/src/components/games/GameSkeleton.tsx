import SkeletonLoader from "../SkeletonLoader";

function GameSkeleton() {
  return (
    <div className="p-7 md:gap-6 md:flex overflow-hidden flex">
      <div className="grid grid-cols-[30px_auto] gap-4 mb-8 h-[80vh] w-full flex-1 max-w-[80vh]">
        <div className="col-start-2">
          <div className="flex items-center gap-2">
            <SkeletonLoader className="w-3 h-3" />  
            <SkeletonLoader className="h-4 w-50" />
          </div>
        </div>

        <SkeletonLoader className="h-full w-[30px]" />

        <SkeletonLoader className="aspect-square h-full w-full" />

        <div className="col-start-2">
          <div className="flex items-center gap-2">
            <SkeletonLoader className="w-3 h-3" />  
            <SkeletonLoader className="h-4 w-50" />
          </div>
        </div>
      </div>

      <SkeletonLoader className="max-h-[80vh] w-[300px] rounded-md overflow-hidden" />
    </div>
  );
}

export default GameSkeleton;
