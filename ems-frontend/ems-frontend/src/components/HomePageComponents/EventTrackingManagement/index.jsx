import React from 'react'
import EventTable from './EventTable';

function EventManagementIndex(props) {

  const {setMenuIndex} = props;
  return (
    <>
      <EventTable setMenuIndex={setMenuIndex}/>
    </>
  )
}

export default EventManagementIndex;