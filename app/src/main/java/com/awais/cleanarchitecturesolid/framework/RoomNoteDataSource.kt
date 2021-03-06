package com.awais.cleanarchitecturesolid.framework

import android.content.Context
import com.awais.cleanarchitecturesolid.framework.db.DatabaseService
import com.awais.cleanarchitecturesolid.framework.db.NoteEntity
import com.awais.core.data.Note
import com.awais.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context) : NoteDataSource {
    private val noteDao by lazy { DatabaseService.getInstance(context).noteDao() }

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long): Note? = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll(): List<Note> = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))

    override suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
}