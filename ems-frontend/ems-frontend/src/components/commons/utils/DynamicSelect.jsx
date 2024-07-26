import React, { useState } from 'react';
import { Select } from 'antd';

const { Option } = Select;

const DynamicSelect = ({ placeholder, display, editable, fetchOptions }) => {
  const [options, setOptions] = useState([]);
  const [loading, setLoading] = useState(false);
  const [dropdownOpen, setDropdownOpen] = useState(false);


  const sendRequest = () => {
    
  }

  const handleDropdownVisibleChange = async (open) => {
    setDropdownOpen(open);
    if (open && options.length === 0) {
      setLoading(true);
      try {
        const data = await fetchOptions();
        setOptions(data);
      } catch (error) {
        console.error('Failed to fetch options:', error);
      } finally {
        setLoading(false);
      }
    }
  };

  const handleSelectChange = (value) => {
    console.log('Selected or typed value:', value);
  };

  return (
    <Select
      mode="tags"
      showSearch
      placeholder={placeholder}
      onDropdownVisibleChange={handleDropdownVisibleChange}
      onChange={handleSelectChange}
      notFoundContent={loading ? <Spin size="small" /> : null}
      display={display ? "block": "none"}
      disabled={!editable}
      open={dropdownOpen || undefined}
    >
      {options.map((option) => (
        <Option key={option.value} value={option.value}>
          {option.label}
        </Option>
      ))}
    </Select>
  );
};

export default DynamicSelect;
