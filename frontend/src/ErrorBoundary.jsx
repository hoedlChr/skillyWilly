import * as React from 'react';

class ErrorBoundary extends React.Component {
  constructor(props) {
    super(props);
    this.state = { 
        hasError: false,
        message: ""
    };
  }

  static getDerivedStateFromError(error) {
    // Update state so the next render will show the fallback UI.
    return { hasError: true, message: error};
  }

  componentDidCatch(error, info) {
  }

  render() {
    if (this.state.hasError) {console.log(this.state)
      // You can render any custom fallback UI
      return <div className='container'>
            <div style={{marginTop: "4em"}} className='alert alert-danger'>
                <h4 className='alert-heading'>{this.state.message.message}</h4>
                <p>{this.state.message.stack}</p>
            </div>
      </div>;
    }

    return this.props.children;
  }
}

export default ErrorBoundary