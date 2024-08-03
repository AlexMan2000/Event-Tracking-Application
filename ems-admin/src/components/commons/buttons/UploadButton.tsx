import React, { useEffect, useState } from 'react';
import { PlusOutlined } from '@ant-design/icons';
import { Image, Upload, message } from 'antd';
import axios, { AxiosProgressEvent } from 'axios';
import { getUploadImageUrl } from '../../../apis/uploadApi';


const getBase64 = (file) =>
  new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = (error) => reject(error);
  });
const UploadButton = (props: any) => {

  const {visible, setProfileImageMetadata} = props;
  const [previewOpen, setPreviewOpen] = useState(false);
  const [previewImage, setPreviewImage] = useState('');
  const [fileList, setFileList] = useState([]);


  useEffect(() => {
    if (!visible) {
        setFileList([]);
        setPreviewImage('');
        setPreviewOpen(false);
    }
  }, [visible])

  const handlePreview = async (file) => {
    console.log(file);
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj);
    }
    setPreviewImage(file.url || file.preview);
    setPreviewOpen(true);
  };

  const customRequest = async ({ file, onSuccess, onError, onProgress }) => {
    const formData = new FormData();



    // file is binary
    formData.append('file', file);
    

    console.log(file)

    try {
      const response = await axios.post(getUploadImageUrl(), formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        onUploadProgress: (event: AxiosProgressEvent) => {
            console.log(event);
          const percent = Math.floor((event.loaded / event.total) * 100);
          onProgress({ percent }, file);
        },
      });

      console.log(response.data)
      setProfileImageMetadata({imageName: file.name, contenType: file.type, imageId: response.data})
      onSuccess(response.data, file);
      message.success(`${file.name} file uploaded successfully.`);
    } catch (error) {
      onError(error);
      message.error(`${file.name} file upload failed.`);
    }
  };

  const handleChange = ({ fileList: newFileList }) => setFileList(newFileList);
  const uploadButton = (
    <button
      style={{
        border: 0,
        background: 'none',
        color: 'black'
      }}
      type="button"
    >
      <PlusOutlined />
      <div
        style={{
          marginTop: 8,
        }}
      >
        {fileList.length > 0 ? "Re-Upload": "Upload"}
      </div>
    </button>
  );
  return (
    <>
      <Upload
        // action={getUploadImageUrl()}
        customRequest={customRequest}
        listType="picture-circle"
        fileList={fileList}
        onPreview={handlePreview}
        onChange={handleChange}
        maxCount={1}
      >
        {uploadButton}
      </Upload>
      {previewImage && (
        <Image
          wrapperStyle={{
            display: 'none',
          }}
          preview={{
            visible: previewOpen,
            onVisibleChange: (visible) => setPreviewOpen(visible),
            afterOpenChange: (visible) => !visible && setPreviewImage(''),
          }}
          src={previewImage}
        />
      )}
    </>
  );
};
export default UploadButton;