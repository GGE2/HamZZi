<<<<<<< HEAD
import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../authSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
=======
import { configureStore, combineReducers } from "@reduxjs/toolkit";
import {
  persistStore,
  persistReducer,
  FLUSH,
  REHYDRATE,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
} from "redux-persist";
import storage from "redux-persist/lib/storage";

import authReducer from "../authSlice";
import pointReducer from "../pointSlice";
import hamStatReducer from "../hamStatSlice";

const persistConfig = {
  key: "root",
  version: 1,
  storage, // localStorage
};

const rootReducer = combineReducers({
  auth: authReducer,
  point: pointReducer,
  hamStat: hamStatReducer,
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
>>>>>>> d71fbd50837b97bd096f25692dba0e251672d352
});
