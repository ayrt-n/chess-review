import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
// 1. Import Font Awesome components
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faExclamation } from '@fortawesome/free-solid-svg-icons';

interface BrilliantProps {
  size?: AnnotationSize;
}

function BrilliantMove({ size = 'md' }: BrilliantProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div
      className={`${container} rounded-full bg-sky-400 flex items-center justify-center`}
    >
      <div className="flex flex-row -space-x-0.5">
        <FontAwesomeIcon 
          icon={faExclamation} 
          style={{ 
            fontSize: `${icon}px`, 
            color: '#ffffff',
            stroke: "#ffffff",
            strokeWidth: "40px" 
          }} 
        />
      </div>
    </div>
  );
}

export default BrilliantMove;