import React from 'react';
import PropTypes from 'prop-types';
import {
  Box, Card, CardActions, CardContent, CardMedia, Typography,
} from '@mui/material';
import CreateButton from './CreateButton';
import EditButton from './EditButton';
import CallButton from './CallButton';

// props comes from Contacts component - users is a list of contact details in object format
function Results({ users }) {
  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="center"
      flexWrap="wrap"
      gap={5}
      my={5}
    >
      {
        users.map((user) => (
          <div key={user.username}>
            <Card sx={{ width: 300 }}>
              <CardMedia
                component="img"
                height="150"
                image="https://27tcx2gd0ls2aa2s03qr8l8n-wpengine.netdna-ssl.com/wp-content/uploads/2018/05/kemptons-blank-profile-picture.jpg"
                alt="Profile picture"
              />
              <CardContent>
                <Typography variant="h5" component="div">
                  { user.name }
                </Typography>
                <Typography variant="body1">
                  Email:
                  {' '}
                  { user.email }
                </Typography>
                <Typography variant="body2">
                  Phone:
                  {' '}
                  { user.phone }
                </Typography>
              </CardContent>
              <CardActions sx={{ justifyContent: 'space-between' }}>
                <CallButton />
                <EditButton user={user} />
              </CardActions>
            </Card>
          </div>
        ))
      }
      <CreateButton fields={Object.keys(users[0])} />
    </Box>
  );
}

Results.propTypes = {
  users: PropTypes.arrayOf(PropTypes.shape({})).isRequired,
};

export default Results;
