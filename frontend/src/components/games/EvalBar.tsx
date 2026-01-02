interface EvalBarProps {
  cp?: number | null;
  mate?: number | null;
}

const EvalBar = ({ cp = 0, mate = null }: EvalBarProps) => {
  const getWhitePercentage = () => {
    if (mate !== null) {
      return mate > 0 ? 100 : 0;
    }

    if (cp === null || cp === undefined) return 50;

    const maxEval = 1000;
    const clampedCp = Math.max(Math.min(cp, maxEval), -maxEval);
    
    return ((clampedCp + maxEval) / (maxEval * 2)) * 100;
  };

  const whiteHeight = getWhitePercentage();
  const displayScore = () => {
    if (mate !== null) return `M${Math.abs(mate)}`;
    if (cp === null || cp === undefined) return "0.0";
    
    const score = cp / 100;
    return score > 0 ? `+${score.toFixed(1)}` : score.toFixed(1);
  };

  return (
    <div className="flex flex-col items-center gap-2 h-full">
      <div className="relative h-full w-full bg-zinc-800 rounded-sm overflow-hidden border border-zinc-600 shadow-inner">
        <div className="absolute bottom-0 w-full bg-white transition-all duration-500 ease-in-out" style={{ height: `${whiteHeight}%` }} />

        <span className={`absolute w-full text-center text-xs font-bold z-10 ${whiteHeight > 50 ? 'bottom-2 text-black' : 'top-2 text-white'}`}>
          {displayScore()}
        </span>
      </div>
    </div>
  );
};

export default EvalBar;