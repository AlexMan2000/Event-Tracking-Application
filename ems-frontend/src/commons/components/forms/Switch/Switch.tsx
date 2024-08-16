import React, { CSSProperties, useState } from 'react';
import "./Switch.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import {getStyles, pagePos} from "@/commons/utils/stylesHandler"

interface SwitchProps {
  // Define your props here
  width?: number
  height?: number
  checked?: boolean;
  onChange?: (checked: boolean) => void;  /** user should pass in a call back function, which will be called with the new value when user click the toggle */
  style?: CSSProperties
  className?: string
}

const Switch: React.FC<SwitchProps> = ({width, height, checked = false, onChange,  style, className}: SwitchProps) => {
  
    const [isChecked, setIsChecked] = useState<boolean>(checked);

    const handleToggle = () => {
        setIsChecked((prev) => !prev);
        if (onChange) {
            // Since this will be executed immediately, before setIsChecked state update, we should return !isChecked
            // , which is the state to be updated(and return to the user)
            onChange(!isChecked);
        }
    }

  
    return (
    <div className={classNamesArgs("switch-container", className, `${isChecked ? "checked":""}`)} onClick ={handleToggle} 
        style={{width: `${width}px`, height: `${height}px`,...style}}>
            <div className={classNamesArgs("toggle", className)}></div>
    </div>
  );
};

export default Switch;