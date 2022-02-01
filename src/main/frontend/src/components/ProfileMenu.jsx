import React from 'react';
import { Box, IconButton, Tooltip } from '@mui/material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

function ProfileMenu() {
  return (
    <Box marginLeft="auto">
      <Tooltip title="Account settings">
        <IconButton>
          <AccountCircleIcon fontSize="large" />
        </IconButton>
      </Tooltip>
    </Box>
  );
}

export default ProfileMenu;
