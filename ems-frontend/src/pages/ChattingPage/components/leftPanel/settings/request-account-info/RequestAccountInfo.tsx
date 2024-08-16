import React, { CSSProperties } from 'react';
import "./RequestAccountInfo.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface RequestAccountInfoProps {
  // Define your props here
  style?: CSSProperties
  className?: string
}

const RequestAccountInfo: React.FC<RequestAccountInfoProps> = ({style, className}: RequestAccountInfoProps) => {
  return (
    <div className={classNamesArgs("request-account-info", className)} style={{...style}}>
      RequestAccountInfo Component
    </div>
  );
};

export default RequestAccountInfo;