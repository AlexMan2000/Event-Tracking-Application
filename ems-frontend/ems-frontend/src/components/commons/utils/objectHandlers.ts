

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