package org.fbarros.mp3clinic.procesor;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.fbarros.mp3clinic.Message;
import org.fbarros.mp3clinic.data.Album;
import org.fbarros.mp3clinic.data.ReportingData;
import org.fbarros.mp3clinic.data.Track;
import org.fbarros.mp3clinic.report.ReporterFactory;

public class DuplicateArtistNamesFinder implements IProcesor {

	private Reporter reporter;

	public DuplicateArtistNamesFinder(ReportingData reportingData) {
		this.reporter = ReporterFactory.getReporter(reportingData);
	}

	@Override
	public List<Message> process(Collection<Album> collection) {
		List<Message> result = new LinkedList<>();

		try {
			Analyzer analyzer = new StandardAnalyzer();
			Directory directory = new RAMDirectory();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter iwriter = new IndexWriter(directory, config);
			collection.stream().forEach(a -> addDocs(iwriter, a.getArtist(), a.getAlbumName()));
			iwriter.close();

			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);

			QueryParser parser = new QueryParser("artist", analyzer);
			List<ScoreDoc> dups = new LinkedList<>();
			
			for (Album album : collection) {
				Query query = parser.createPhraseQuery("artist", album.getAlbumName());
				TopDocs hits = isearcher.search(query, 30);
				dups.clear();
				for (int i = 0; i < hits.scoreDocs.length; i++) {
					if (hits.getMaxScore() != hits.scoreDocs[i].score)
						dups.add(hits.scoreDocs[i]);
				}
				result.add(reporter.createMessage(album, createMessageDups(dups)));
			}

			ireader.close();
			directory.close();
		} catch (Exception e) {
			System.out.println("Error! " + e);
		}
		return result;
	}

	// TODO: cuquificar
	private String createMessageDups(List<ScoreDoc> dups) {
		StringBuilder sb = new StringBuilder();
		for (ScoreDoc scoreDoc : dups) {
			sb.append(scoreDoc.toString() + ",");
		}
		return null;
	}

	private static void addDocs(IndexWriter iwriter, String artist, String album) {
		Document doc = new Document();
		doc.add(new Field("artist", artist, TextField.TYPE_STORED));
		doc.add(new Field("album", album, TextField.TYPE_STORED));
		try {
			iwriter.addDocument(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc = new Document();

	}

	public boolean containsDuplicates(Album album) {
		boolean[] exist = new boolean[album.getNumberOfTracks()];
		for (Track track : album.getTracks()) {
			if (track.getNumber() > 0 && track.getNumber() <= album.getNumberOfTracks()
					&& exist[track.getNumber() - 1]) {
				return true;
			} else {
				exist[track.getNumber() - 1] = true;
			}
		}
		return false;
	}

}
