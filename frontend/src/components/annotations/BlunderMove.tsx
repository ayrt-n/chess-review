import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faXmark } from '@fortawesome/free-solid-svg-icons';

interface BlunderProps {
  size?: AnnotationSize;
}

function BlunderMove({ size = 'md' }: BlunderProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div className={`${container} rounded-full bg-red-600 flex items-center justify-center`} >
      <FontAwesomeIcon 
        icon={faXmark} 
        style={{ 
          fontSize: `${icon}px`, 
          color: '#ffffff',
          stroke: "#ffffff",
          strokeWidth: "40px" 
        }} 
      />
    </div>
  );
}

export default BlunderMove;
