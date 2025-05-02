import { useEffect, useState } from 'react'
import React from 'react';

const LoadingBar = () => {
    const [randomNumber, setRandomNumber] = useState(Math.floor(Math.random() * 100) + 1);

    useEffect(() => {
      const interval = setInterval(() => {
      setRandomNumber(Math.floor(Math.random() * 99) + 1);
      }, 1000);

      return () => clearInterval(interval); // Cleanup on unmount
    }, []);

    return (
      <div className="progress" style={{ width: "100%" }}>
        <div
          className="progress-bar"
          role="progressbar"
          style={{ width: `${randomNumber}%` }}
          aria-valuenow={randomNumber}
          aria-valuemin="0"
          aria-valuemax="100"
        >
          {randomNumber}%
        </div>
      </div>
    )
};


export default LoadingBar;