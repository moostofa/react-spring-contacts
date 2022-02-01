import React, { useState } from 'react';
import { Button } from '@mui/material';
import { LoadingButton } from '@mui/lab';
import CallIcon from '@mui/icons-material/Call';

function CallButton() {
  const [loading, setLoading] = useState(false);

  const handleClick = () => {
    setLoading(!loading);
  };

  return (
    <>
      <LoadingButton
        startIcon={<CallIcon />}
        variant="contained"
        color="success"
        size="small"
        loading={loading}
        onClick={handleClick}
      >
        Call
      </LoadingButton>
      {
        loading && (
        <Button size="small" variant="contained" color="error" onClick={handleClick}>End</Button>
        )
    }
    </>
  );
}

export default CallButton;
