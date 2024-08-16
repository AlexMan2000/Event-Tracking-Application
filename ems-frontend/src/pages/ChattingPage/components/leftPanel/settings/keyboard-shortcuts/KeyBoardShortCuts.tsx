import React, { CSSProperties } from 'react';
import "./KeyBoardShortCuts.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface KeyBoardShortCutsProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const KeyBoardShortCuts: React.FC<KeyBoardShortCutsProps> = ({style, className}: KeyBoardShortCutsProps) => {
  return (
    <div className={classNamesArgs("key-board-short-cuts", className)} style={{...style}}>
      KeyBoardShortCuts Component
    </div>
  );
};

export default KeyBoardShortCuts;