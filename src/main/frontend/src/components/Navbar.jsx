import React from 'react';
import {
  AppBar, CssBaseline, Toolbar, Typography, IconButton, InputBase, Box,
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import ProfileMenu from './ProfileMenu';

// navbar for moving between pages (not functional - just demo)
function Navbar() {
  return (
    <AppBar sx={{ maxHeight: 75, backgroundColor: 'cornflowerblue' }} position="static">
      <CssBaseline />
      <Toolbar>
        <IconButton>
          <MenuIcon fontSize="large" />
        </IconButton>
        <Typography sx={{ mr: 2 }} variant="h5" color="black">
          Contacts
        </Typography>
        <Box sx={{
          backgroundColor: 'aliceblue',
          borderColor: 'red',
          borderRadius: 2,
          paddingLeft: 2,
          ':hover': { backgroundColor: 'azure' },
        }}
        >
          <InputBase size="large" placeholder="Search contacts..." />
        </Box>
        <ProfileMenu />
      </Toolbar>
    </AppBar>
  );
}

export default Navbar;
