import React, { useState, useEffect } from 'react';
import Results from './Results';

// fetch & display a list of users from jsonplaceholder
function Contacts() {
  const [contacts, setContacts] = useState([{}]);

  const getContacts = async () => {
    const response = await fetch('https://jsonplaceholder.typicode.com/users');
    const users = await response.json();
    setContacts(users);
  };

  useEffect(() => {
    getContacts();
  }, []);

  return <Results users={contacts} />;
}

export default Contacts;
