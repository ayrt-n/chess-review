interface SkeletonLoaderProps {
  width?: string;
  height?: string;
  className?: string;
  rounded?: "none" | "sm" | "md" | "lg" | "full";
}

export default function SkeletonLoader({
  className = "",
  rounded = "sm",
}: SkeletonLoaderProps) {
  const roundedClasses = {
    none: "rounded-none",
    sm: "rounded-sm",
    md: "rounded-md",
    lg: "rounded-lg",
    full: "rounded-full",
  };

  return (
    <div className={`relative overflow-hidden bg-zinc-700/50 ${roundedClasses[rounded]} ${className}`}>
      <div className="absolute inset-0 -translate-x-full animate-[shimmer_1.5s_infinite] bg-gradient-to-r from-transparent via-zinc-500/20 to-transparent" />
    </div>
  );
}

