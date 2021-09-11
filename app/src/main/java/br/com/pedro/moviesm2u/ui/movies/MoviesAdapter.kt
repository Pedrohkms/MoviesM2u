package br.com.pedro.moviesm2u.ui.movies

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pedro.moviesm2u.data.entities.Movie
import br.com.pedro.moviesm2u.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class MoviesAdapter() :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<Movie>()

    fun setItems(items: List<Movie>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(items[position])
}

class MovieViewHolder(
    private val itemBinding: ItemMovieBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var movie: Movie

    @SuppressLint("SetTextI18n")
    fun bind(item: Movie) {
        this.movie = item
        itemBinding.titleMovie.text = item.title
        val imagePoster = item.poster_path
        Glide.with(itemBinding.root)
            .load("https://image.tmdb.org/t/p/w342${item.poster_path}")
            .into(itemBinding.itemMoviePoster)
        Log.d("imagem", "https://image.tmdb.org/t/p/w342${item.poster_path}")
        Log.d("imagem", imagePoster.toString())
    }
}