const FILE_NAME_TO_LANGUAGE_MAP = {
  ts: 'typescript',
  js: 'javascript',
  java: 'java',
  css: 'css',
  html: 'html'
}

export const getLanguageByFileName = fileName => {
  const arr = fileName.split('.')
  if (arr.length < 2) {
    return 'text'
  }
  const suffix = arr[arr.length - 1]
  return FILE_NAME_TO_LANGUAGE_MAP[suffix] ? FILE_NAME_TO_LANGUAGE_MAP[suffix] : 'text'
}
