import { useState } from 'react';
import Editor from 'react-simple-wysiwyg';

function FCKEditor() {
  const [html, setHtml] = useState('');
  
  function onChange(e) {
    setHtml(e.target.value);
  }

  return (
    <Editor value={html} onChange={onChange} />
  );
}
export default FCKEditor;
