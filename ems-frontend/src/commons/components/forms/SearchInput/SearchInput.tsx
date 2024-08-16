import React, { CSSProperties, useState } from 'react';
import "./SearchInput.css"
import {classNamesArgs} from "@/commons/utils/classNameHandler"
import ImageBox from '../../medias/ImageBox';
import SearchIcon from "@/assets/search-icon.svg"

interface SearchInputProps {
  // Define your props here
  onFocus?: () => {}
  onChange?: (value) => void;
  style?: CSSProperties
  className?: string
}

const SearchInput: React.FC<SearchInputProps> = ({style, className}: SearchInputProps) => {
    
    const [value, setValue] = useState<string>("");
    const [isFocused, setIsFocused] = useState<boolean>(false);
  

    const handleOnChange = (e: any) => {
        e.preventDefault();
        setValue(e.target.value);
    }


    return (
    <div className={classNamesArgs("search-input-container", className)} style={{...style}}>
       <ImageBox src={SearchIcon} width={24} height={24}></ImageBox>
       <input 
            value = {value}
            className={classNamesArgs("search-input", className)}
            onFocus={() => setIsFocused(true)}
            onBlur={() => setIsFocused(false)}
            onChange={handleOnChange}
            placeholder={isFocused ? "" : "Click to search"}
            ></input>
    </div>
  );
};

export default SearchInput;