# MVVMPaging

This app works by fetching dummy JSON data from https://reqres.in/api/users

Users are fetched 3 at a time, and the call to fetch more data is triggered by Android's paging library. 

The UI uses LiveData to observe for changes to the database and updates itself when a network request pushes new data.
