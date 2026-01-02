import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';

interface BrilliantProps {
  size?: AnnotationSize;
}

function BrilliantMove({ size = 'md' }: BrilliantProps) {
  const { container, icon } = annotationSizes[size];
  const fontSize = Math.round(icon * 0.9);

  return (
    <div
      className={`${container} rounded-full bg-sky-400 flex items-center justify-center`}
    >
      <span
        style={{
          fontSize: `${fontSize}px`,
          fontWeight: 800,
          color: '#ffffff',
          lineHeight: 1,
        }}
      >
        !!
      </span>
    </div>
  );
}

export default BrilliantMove;

