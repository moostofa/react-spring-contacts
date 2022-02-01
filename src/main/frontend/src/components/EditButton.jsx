import React from 'react';
import PropTypes from 'prop-types';
import { Button } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import PopupModal from './PopupModal';

// an edit button which displays a popup form to edit a contact's details
function EditButton({ user }) {
  // controls the opening & closing of popup modal
  const [open, setOpen] = React.useState(false);
  const openModal = () => setOpen(true);
  const closeModal = () => setOpen(false);

  return (
    <>
      <Button
        startIcon={<EditIcon />}
        size="small"
        variant="contained"
        color="info"
        onClick={openModal}
      >
        Edit details
      </Button>
      { open && <PopupModal type="edit" user={user} closeModal={closeModal} /> }
    </>
  );
}

EditButton.propTypes = {
  user: PropTypes.shape({}).isRequired,
};

export default EditButton;
