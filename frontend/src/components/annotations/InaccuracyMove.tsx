import type { AnnotationSize } from '../../types/annotations';
import { annotationSizes } from '../../types/annotations';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faQuestion } from '@fortawesome/free-solid-svg-icons';

interface InaccuracyProps {
  size?: AnnotationSize;
}

function InaccuracyMove({ size = 'md' }: InaccuracyProps) {
  const { container, icon } = annotationSizes[size];

  return (
    <div className={`${container} rounded-full bg-yellow-400 flex items-center justify-center`} >
      <div className="flex flex-row -space-x-0.5 items-center">
        <FontAwesomeIcon 
          icon={faQuestion} 
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

export default InaccuracyMove;