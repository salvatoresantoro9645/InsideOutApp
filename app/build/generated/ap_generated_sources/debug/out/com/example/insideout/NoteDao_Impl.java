package com.example.insideout;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Note> __insertionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __deletionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __updateAdapterOfNote;

  public NoteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNote = new EntityInsertionAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Note` (`noteId`,`date`,`text`,`authorId`,`authorName`,`mood`,`dateTime`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.noteId);
        if (value.date == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.date);
        }
        if (value.text == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.text);
        }
        if (value.authorId == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.authorId);
        }
        if (value.authorName == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.authorName);
        }
        if (value.mood == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.mood);
        }
        if (value.dateTime == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.dateTime);
        }
      }
    };
    this.__deletionAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Note` WHERE `noteId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.noteId);
      }
    };
    this.__updateAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Note` SET `noteId` = ?,`date` = ?,`text` = ?,`authorId` = ?,`authorName` = ?,`mood` = ?,`dateTime` = ? WHERE `noteId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Note value) {
        stmt.bindLong(1, value.noteId);
        if (value.date == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.date);
        }
        if (value.text == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.text);
        }
        if (value.authorId == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.authorId);
        }
        if (value.authorName == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.authorName);
        }
        if (value.mood == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.mood);
        }
        if (value.dateTime == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.dateTime);
        }
        stmt.bindLong(8, value.noteId);
      }
    };
  }

  @Override
  public void insertNotes(final Note... notes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNote.insert(notes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNotes(final Note... notes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNote.handleMultiple(notes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllNotes(final Note... notes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNote.handleMultiple(notes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateNotes(final Note... notes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNote.handleMultiple(notes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Note[] loadAllNotesForReqDate(final String reqAuthorId, final String reqDate) {
    final String _sql = "SELECT * FROM note WHERE authorId == ? AND date == ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (reqAuthorId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, reqAuthorId);
    }
    _argIndex = 2;
    if (reqDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, reqDate);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNoteId = CursorUtil.getColumnIndexOrThrow(_cursor, "noteId");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
      final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
      final int _cursorIndexOfAuthorName = CursorUtil.getColumnIndexOrThrow(_cursor, "authorName");
      final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
      final Note[] _result = new Note[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Note _item;
        _item = new Note();
        _item.noteId = _cursor.getInt(_cursorIndexOfNoteId);
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _item.date = null;
        } else {
          _item.date = _cursor.getString(_cursorIndexOfDate);
        }
        if (_cursor.isNull(_cursorIndexOfText)) {
          _item.text = null;
        } else {
          _item.text = _cursor.getString(_cursorIndexOfText);
        }
        if (_cursor.isNull(_cursorIndexOfAuthorId)) {
          _item.authorId = null;
        } else {
          _item.authorId = _cursor.getString(_cursorIndexOfAuthorId);
        }
        if (_cursor.isNull(_cursorIndexOfAuthorName)) {
          _item.authorName = null;
        } else {
          _item.authorName = _cursor.getString(_cursorIndexOfAuthorName);
        }
        if (_cursor.isNull(_cursorIndexOfMood)) {
          _item.mood = null;
        } else {
          _item.mood = _cursor.getString(_cursorIndexOfMood);
        }
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _item.dateTime = null;
        } else {
          _item.dateTime = _cursor.getString(_cursorIndexOfDateTime);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Note[] loadAllNotes() {
    final String _sql = "SELECT * FROM note";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNoteId = CursorUtil.getColumnIndexOrThrow(_cursor, "noteId");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
      final int _cursorIndexOfAuthorId = CursorUtil.getColumnIndexOrThrow(_cursor, "authorId");
      final int _cursorIndexOfAuthorName = CursorUtil.getColumnIndexOrThrow(_cursor, "authorName");
      final int _cursorIndexOfMood = CursorUtil.getColumnIndexOrThrow(_cursor, "mood");
      final int _cursorIndexOfDateTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dateTime");
      final Note[] _result = new Note[_cursor.getCount()];
      int _index = 0;
      while(_cursor.moveToNext()) {
        final Note _item;
        _item = new Note();
        _item.noteId = _cursor.getInt(_cursorIndexOfNoteId);
        if (_cursor.isNull(_cursorIndexOfDate)) {
          _item.date = null;
        } else {
          _item.date = _cursor.getString(_cursorIndexOfDate);
        }
        if (_cursor.isNull(_cursorIndexOfText)) {
          _item.text = null;
        } else {
          _item.text = _cursor.getString(_cursorIndexOfText);
        }
        if (_cursor.isNull(_cursorIndexOfAuthorId)) {
          _item.authorId = null;
        } else {
          _item.authorId = _cursor.getString(_cursorIndexOfAuthorId);
        }
        if (_cursor.isNull(_cursorIndexOfAuthorName)) {
          _item.authorName = null;
        } else {
          _item.authorName = _cursor.getString(_cursorIndexOfAuthorName);
        }
        if (_cursor.isNull(_cursorIndexOfMood)) {
          _item.mood = null;
        } else {
          _item.mood = _cursor.getString(_cursorIndexOfMood);
        }
        if (_cursor.isNull(_cursorIndexOfDateTime)) {
          _item.dateTime = null;
        } else {
          _item.dateTime = _cursor.getString(_cursorIndexOfDateTime);
        }
        _result[_index] = _item;
        _index ++;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
