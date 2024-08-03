

export const truncateObjectByExcluding = (obj: any, fieldsToExclude: string[]) => {
    return Object.keys(obj).reduce((result, key) => {
        if (!fieldsToExclude.includes(key)) {
          result[key] = obj[key];
        }
        return result;
      }, {});
}


export const truncateObjectByIncluding = (obj: any, fieldsToInclude: string[]) => {
  return Object.keys(obj).reduce((result, key) => {
      if (fieldsToInclude.includes(key)) {
        result[key] = obj[key];
      }
      return result;
    }, {});
}


export const sortForeignRelationBy = (obj: any, fieldName: string, ascending=true) => {
  Object.keys(obj).forEach(key => {
    if (Array.isArray(obj[key])) {
      obj[key].sort((a, b) => ascending? a[fieldName] - b[fieldName]: b[fieldName] - a[fieldName]);
    }
  });
}


export const copyFromObjToForm = (obj: any, formData: FormData, fieldsToInclude: string[] = []) => {
  if (fieldsToInclude?.length == 0) {
    console.log("haha'")
    Object.entries(obj).forEach(([key, value]: [string, any]) => {
      console.log(key + value)
      if (Array.isArray(value)) {
        value.forEach((item, index) => {
          formData.append(`${key}[${index}]`, item);
        });
      } else if (value instanceof File) {
        formData.append(key, value);
        console.log(formData);
      } else {
        formData.append(key, value);
        console.log(formData);
      }
    });
  } else {
    Object.entries(obj).forEach(([key, value]: [string, any]) => {
      if (!fieldsToInclude.includes(key)) {
        return;
      }
      if (Array.isArray(value)) {
        value.forEach((item, index) => {
          formData.append(`${key}[${index}]`, item);
        });
      } else if (value instanceof File) {
        formData.append(key, value);
      } else {
        formData.append(key, value);
      }
    });
  }

  console.log(formData);
}