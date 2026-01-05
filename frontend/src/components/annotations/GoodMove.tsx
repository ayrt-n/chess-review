import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
// 1. Import Font Awesome components
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';

interface GoodProps {
  size?: AnnotationSize;
}

function GoodMove({ size = 'md' }: GoodProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div
      className={`${container} rounded-full bg-lime-500 flex items-center justify-center`}
    >
      {/* 2. Replace SVG with FontAwesomeIcon */}
      <FontAwesomeIcon 
        icon={faThumbsUp} 
        style={{ 
          fontSize: `${icon}px`, 
          color: '#ffffff' 
        }} 
      />
    </div>
  );
}

export default GoodMove;
