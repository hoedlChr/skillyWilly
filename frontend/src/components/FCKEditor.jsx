import { useState } from 'react';
import Editor from 'react-simple-wysiwyg';

function FCKEditor({value, changeHandler}) {
  
  function onChange(e) {
    changeHandler(e.target.value);
  }

  return (
    <Editor value={value} onChange={onChange} />
  );
}
export default FCKEditor;
