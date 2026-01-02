import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';

interface BestProps {
  size?: AnnotationSize;
}

function BestMove({ size = 'md' }: BestProps) {
  const { container, icon } = annotationSizes[size];
  const starSize = Math.round(icon * 1.25);

  return (
    <div
      className={`${container} rounded-full bg-lime-500 flex items-center justify-center`}
    >
      <svg
        width={starSize}
        height={starSize}
        viewBox="0 0 24 24"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M12 2L14.09 8.26L21 9.27L16 14.14L17.18 21.02L12 17.77L6.82 21.02L8 14.14L3 9.27L9.91 8.26L12 2Z"
          fill="#ffffff"
          stroke="#ffffff"
          strokeWidth="1"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
      </svg>
    </div>
  );
}

export default BestMove;

