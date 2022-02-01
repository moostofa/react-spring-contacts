import React from 'react';
import PropTypes from 'prop-types';
import {
  Box, Button, FormControl, Grid, Modal, TextField,
} from '@mui/material';
import modalStyle from './styles/ModalStyle';

function PopupModal({
  type, user, fields, closeModal,
}) {
  // display an editable textfield for each contact field
  const displayField = ([key, value]) => (
    <Grid key={key} item xs={6}>
      <Grid item xs={2}>
        { key[0].toUpperCase() + key.slice(1) }
      </Grid>
      <Grid item xs={8}>
        <FormControl fullWidth>
          <TextField size="small" label={key} defaultValue={value} />
        </FormControl>
      </Grid>
    </Grid>
  );

  // modal for editing should contain pre-filled data of the contact
  const displayEditModal = () => (
    <>
      <h1>
        Edit details for
        {' '}
        { user.name }
      </h1>
      {
        Object.entries(user)
          .map(([key, value]) => (
            typeof value === 'object'
              ? Object.entries(value)
                .map(([nestedKeys, nestedValues]) => (
                  displayField([nestedKeys, nestedValues])
                ))
              : displayField([key, value])
          ))
      }
    </>
  );

  // contact creation modal will have empty textfields
  const displayCreationModal = () => (
    <>
      <Grid item xs={12}>
        <h1>Create a new contact</h1>
      </Grid>
      {
        fields.map((field) => (
          displayField([field, ''])
        ))
      }
    </>
  );

  return (
    <Modal
      open
      onClose={closeModal}
    >
      <Box sx={modalStyle}>
        <Grid container spacing={1}>
          {
          type === 'edit' ? displayEditModal() : displayCreationModal()
        }
          <Grid item marginLeft="auto">
            <Button size="large" color="success" variant="contained" sx={{ marginRight: 2 }} onClick={closeModal}>Save</Button>
            <Button size="large" color="info" variant="contained" onClick={closeModal}>Cancel</Button>
          </Grid>
        </Grid>
      </Box>
    </Modal>
  );
}

PopupModal.propTypes = {
  type: PropTypes.string.isRequired,
  user: PropTypes.shape({
    name: PropTypes.string,
  }),
  fields: PropTypes.arrayOf(PropTypes.string),
  closeModal: PropTypes.func.isRequired,
};

PopupModal.defaultProps = {
  user: {},
  fields: [],
};

export default PopupModal;
