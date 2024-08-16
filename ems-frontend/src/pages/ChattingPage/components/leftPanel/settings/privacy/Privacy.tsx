import React, { CSSProperties } from 'react';
import "./Privacy.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface PrivacyProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const Privacy: React.FC<PrivacyProps> = ({style, className}: PrivacyProps) => {
  return (
    <div className={classNamesArgs("privacy-container", className)} style={{...style}}>
      Privacy Component
    </div>
  );
};

export default Privacy;