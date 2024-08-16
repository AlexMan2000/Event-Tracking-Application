import React, { CSSProperties } from 'react';
import "./BackArrowButton.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"
import LeftArrowIcon from "@/assets/arrows/left-arrow-black.svg"
import ImageBox from '../../medias/ImageBox';


interface BackArrowButtonProps {
  // Define your props here
  onClick: ()=>void
  style?: CSSProperties
  className?: string
}

const BackArrowButton: React.FC<BackArrowButtonProps> = ({onClick, style, className}: BackArrowButtonProps) => {
  return (
    <div className={classNamesArgs("back-button-container", className)}>
      <ImageBox src={LeftArrowIcon} width={35} height={35} onClick={onClick}
      />
    </div>
  );
};

export default BackArrowButton;