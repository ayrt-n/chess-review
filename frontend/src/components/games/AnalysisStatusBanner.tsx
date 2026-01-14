import type { AnalysisStatus } from "../../types/api";

const STATUS_MESSAGES: Record<AnalysisStatus, string> = {
  'PENDING': 'Waiting to start analysis...',
  'PROCESSING': 'Analyzing moves with Stockfish...',
  'REVIEWING': 'Generating move commentary...',
  'COMPLETED': 'Analysis complete',
  'FAILED': 'Analysis failed',
};

function AnalysisStatusBanner({ status } : { status: AnalysisStatus }) {
  return (
    <div className="w-full max-w-4xl mb-4 px-4 py-3 bg-amber-900/50 border border-amber-700/70 rounded-lg flex items-center gap-3">
      <div className="w-4 h-4 border-2 border-amber-400 border-t-transparent rounded-full animate-spin" />
      <span className="text-amber-200">{STATUS_MESSAGES[status]}</span>
    </div>
  );
}

export default AnalysisStatusBanner;
