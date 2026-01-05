import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
import { faStar } from '@fortawesome/free-solid-svg-icons';

interface GreatProps {
  size?: AnnotationSize;
}

function GreatMove({ size = 'md' }: GreatProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div
      className={`${container} rounded-full bg-lime-500 flex items-center justify-center`}
    >
      <FontAwesomeIcon 
        icon={faStar} 
        style={{ 
          fontSize: `${icon}px`,
          color: '#ffffff' 
        }} 
      />
    </div>
  );
}

export default GreatMove;