import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { IconButton, Tooltip } from '@mui/material';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import PopupModal from './PopupModal';

// a button which displays a new contact creation form when clicked
function CreateButton({ fields }) {
  const [visible, setVisible] = useState(false);

  // controls the opening & closing of popup modal
  const [open, setOpen] = React.useState(false);
  const openModal = () => setOpen(true);
  const closeModal = () => setOpen(false);

  const toggleVisibility = () => {
    if (window.scrollY > 80) setVisible(true);
    else setVisible(false);
  };

  useEffect(() => {
    window.addEventListener('scroll', toggleVisibility);
    return () => window.removeEventListener('scroll');
  }, []);

  return (
    <div>
      {
        visible
        && (
          <Tooltip title="Add contact">
            <IconButton sx={{ bottom: 30, right: 50, position: 'fixed' }} onClick={openModal}>
              <AddCircleIcon sx={{ fontSize: 80, color: 'deepskyblue' }} />
            </IconButton>
          </Tooltip>
        )
      }
      { open && <PopupModal type="create" fields={fields} closeModal={closeModal} /> }
    </div>
  );
}

CreateButton.propTypes = {
  fields: PropTypes.arrayOf(PropTypes.string).isRequired,
};

export default CreateButton;
